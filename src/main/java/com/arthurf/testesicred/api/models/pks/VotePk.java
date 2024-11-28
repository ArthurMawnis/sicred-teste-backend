package com.arthurf.testesicred.api.models.pks;

import com.arthurf.testesicred.api.models.Vote;

/**
 * VotePk
 * 
 * Model class for {@link Vote} primary key
 */
public class VotePk {

    private String agendaId;
    private String memberId;

    public VotePk() {}

    /**
     * @param agendaId
     * @param memberId
     */
    public VotePk(String agendaId, String memberId) {
        this.agendaId = agendaId;
        this.memberId = memberId;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
