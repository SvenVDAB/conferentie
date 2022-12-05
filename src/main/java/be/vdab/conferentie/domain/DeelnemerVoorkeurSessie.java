package be.vdab.conferentie.domain;

public class DeelnemerVoorkeurSessie {
    private final long deelnemerId;
    private final long sessieId;

    public DeelnemerVoorkeurSessie(long deelnemerId, long sessieId) {
        this.deelnemerId = deelnemerId;
        this.sessieId = sessieId;
    }

    public long getDeelnemerId() {
        return deelnemerId;
    }

    public long getSessieId() {
        return sessieId;
    }
}
