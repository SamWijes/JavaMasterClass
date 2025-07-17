package dev.JPA.music;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album implements Comparable<Album> {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "album_id")
	private int albumId;

	@Column(name = "album_name")
	private String albumName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "album_id")
	private List<Song> songs=new ArrayList<>();

	public Album() {
	}

	public Album(String albumName) {
		this.albumName = albumName;
	}

	public Album(int albumId, String albumName) {
		this.albumId = albumId;
		this.albumName = albumName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	@Override
	public String toString() {
		return "Album{" +
				"albumId=" + albumId +
				", albumName='" + albumName + '\'' +
				",songs= " +songs +
				'}';
	}

	@Override
	public int compareTo(Album o) {
		return this.albumName.compareTo(o.albumName);
	}
}
