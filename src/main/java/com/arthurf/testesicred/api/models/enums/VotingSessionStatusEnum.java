package com.arthurf.testesicred.api.models.enums;

/**
 * VotingSessionStatusEnum
 * 
 * Enum class for voting session status
 */
public enum VotingSessionStatusEnum {

    /**
     * The voting session is open
     */
    OPEN("OPEN"),
    /**
     * The voting session is closed
     */
    CLOSED("CLOSED");

    private String status;

    VotingSessionStatusEnum(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Get the enum from a string
     * 
     * @param status The status string
     * @return The enum
     */
    public static VotingSessionStatusEnum fromString(final String status) {
        for (final VotingSessionStatusEnum votingSessionStatusEnum : VotingSessionStatusEnum.values()) {
            if (votingSessionStatusEnum.getStatus().equalsIgnoreCase(status)) {
                return votingSessionStatusEnum;
            }
        }

        return null;
    }
}
