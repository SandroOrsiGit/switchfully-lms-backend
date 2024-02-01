package com.switchfully.switchfullylmsbackend.dtos.codelabs;

public class UpdateCodelabProgressDto {

    private Long codelabId;
    private Long progressId;

    public UpdateCodelabProgressDto() {}

    public UpdateCodelabProgressDto(Long codelabId, Long progressId) {
        this.codelabId = codelabId;
        this.progressId = progressId;
    }

    public Long getCodelabId() {
        return codelabId;
    }

    public void setCodelabId(Long codelabId) {
        this.codelabId = codelabId;
    }

    public Long getProgressId() {
        return progressId;
    }

    public void setProgressId(Long progressId) {
        this.progressId = progressId;
    }

    @Override
    public String toString() {
        return "UpdateCodelabProgressDto{" +
                "codelabId=" + codelabId +
                ", progressId=" + progressId +
                '}';
    }

}
