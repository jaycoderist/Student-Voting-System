public class Candidate {
    private int id;
    private String name;
    private String position;
    private int voteCount;

    public Candidate(int id, String name, String position, int voteCount) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.voteCount = voteCount;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getVoteCount() { return voteCount; }
}