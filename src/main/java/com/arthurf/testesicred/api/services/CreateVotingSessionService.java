package com.arthurf.testesicred.api.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.arthurf.testesicred.api.dtos.CreateVotingSessionDTO;
import com.arthurf.testesicred.api.exceptions.BusinessException;
import com.arthurf.testesicred.api.models.Agenda;
import com.arthurf.testesicred.api.models.VotingSession;
import com.arthurf.testesicred.api.models.enums.VotingSessionStatusEnum;
import com.arthurf.testesicred.api.repositories.AgendaRepository;
import com.arthurf.testesicred.api.repositories.VotingSessionRepository;
import com.arthurf.testesicred.api.utils.BusinessUtils;

import reactor.core.publisher.Mono;

/**
 * CreateVotingSession
 * 
 * Service class for creating a voting session
 */
@Service
public class CreateVotingSessionService {

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    /**
     * Default duration for the voting session in seconds.
     */
    @Value("${domain.voting-session.default-duration}")
    private String defaultDuration;

    /**
     * Execute the service
     * 
     * @param createVotingSessionDTO The data to create the voting session
     */
    public Mono<VotingSession> execute(final CreateVotingSessionDTO createVotingSessionDTO) {
        final List<String> errors = validate(createVotingSessionDTO);

        if (!errors.isEmpty()) {
            throw new BusinessException(BusinessUtils.parseErrorsList(errors), HttpStatus.BAD_REQUEST);
        }

        final var defaultDurationAsNumber = Long.parseLong(this.defaultDuration);
        final var agenda = agendaRepository.findById(UUID.fromString(createVotingSessionDTO.getAgendaId()))
                .orElseThrow(() -> new BusinessException("Agenda not found.", HttpStatus.NOT_FOUND));

        final boolean hasSessionAlreadyOpen = getOpenSession(agenda);

        if (hasSessionAlreadyOpen) {
            throw new BusinessException("There is already an open session for this agenda.",
                    HttpStatus.PRECONDITION_FAILED);
        }

        var votingSession = new VotingSession();
        votingSession.setId(UUID.randomUUID());
        votingSession.setAgendaId(agenda.getId().toString());
        votingSession.setDuration(
                ObjectUtils.isEmpty(createVotingSessionDTO.getDuration()) ? defaultDurationAsNumber
                        : createVotingSessionDTO.getDuration());
        votingSession.setStartedAt(LocalDateTime.now());
        // Due to the simplicity, we are assuming the voting session start immediately
        votingSession.setStatus(VotingSessionStatusEnum.OPEN);

        return Mono.just(votingSessionRepository.save(votingSession));
    }

    /**
     * Check if there is an open session for the agenda
     * 
     * @param agenda The agenda to check
     * @return True if there is an open session, false otherwise
     */
    private boolean getOpenSession(Agenda agenda) {
        final List<VotingSession> votingSessionOpt = votingSessionRepository.findByAgendaIdAndStatus(
                agenda.getId().toString(),
                VotingSessionStatusEnum.OPEN);
        return !votingSessionOpt.isEmpty();
    }

    /**
     * Validate the data
     * 
     * @param createVotingSessionDTO The data to validate
     * @return A list of errors
     */
    private List<String> validate(final CreateVotingSessionDTO createVotingSessionDTO) {
        final List<String> errors = new ArrayList<>();

        if (ObjectUtils.isEmpty(createVotingSessionDTO)) {
            errors.add("The data is required.");
            return errors;
        }

        if (ObjectUtils.isEmpty(createVotingSessionDTO.getAgendaId())) {
            errors.add("The id of the agenda is required.");
        }

        if (!BusinessUtils.isUUID(createVotingSessionDTO.getAgendaId())) {
            errors.add("The id of the agenda is not a valid UUID.");
            return errors;
        }

        if (createVotingSessionDTO.getDuration() != null && createVotingSessionDTO.getDuration() < 60) {
            errors.add("The duration of the voting session must be at least 60 seconds.");
        }

        if (!agendaRepository.existsById(UUID.fromString(createVotingSessionDTO.getAgendaId()))) {
            errors.add("this agenda does not exist.");
        }

        return errors;
    }
}
