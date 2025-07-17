package dev.JPA.music;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song implements Comparable<Song> {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_id")
	private int songId;

	@Column(name = "song_title")
	private String trackName;


	public Song() {
	}

	public Song(String trackName) {
		this.trackName = trackName;
	}

	public Song(int songId, String trackName) {
		this.songId = songId;
		this.trackName = trackName;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	@Override
	public String toString() {
		return "Song{" +
				"songId=" + songId +
				", trackName='" + trackName + '\'' +
				'}';
	}

	@Override
	public int compareTo(Song o) {
		return this.trackName.compareTo(o.trackName);
	}
}
