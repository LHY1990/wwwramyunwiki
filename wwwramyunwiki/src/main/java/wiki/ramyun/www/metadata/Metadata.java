package wiki.ramyun.www.metadata;

public class Metadata {
	private String name;
	private int visited;
	private int likes;
	private int reporting;
	private String username;
	
	
	public Metadata() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVisited() {
		return visited;
	}
	public void setVisited(int visited) {
		this.visited = visited;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getReporting() {
		return reporting;
	}
	public void setReporting(int reporting) {
		this.reporting = reporting;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Metadata [name=" + name + ", visited=" + visited + ", likes=" + likes + ", reporting=" + reporting
				+ ", username=" + username + "]";
	}
	
}
