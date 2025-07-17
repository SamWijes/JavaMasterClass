package dev.JPA;

import dev.JPA.music.Album;
import dev.JPA.music.Artist;
import dev.JPA.music.Song;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongQuery {

	public static void main(String[] args) {
		try(EntityManagerFactory emf= Persistence.createEntityManagerFactory("dev.JPA.music");
			EntityManager em= emf.createEntityManager())

		{
			EntityTransaction transaction=em.getTransaction();
			transaction.begin();


//			var list=getArtistWithSong(em,"%Storm%");
//
//			list.forEach(System.out::println);

			var matches=matchSongBuilder(em,"%Storm%");

			matches.forEach(m-> System.out.printf("%-30s %-30s %s %n",(String)m[0],(String)m[1],(String)m[2]));

			transaction.commit();


		}
	}


	private static List<String> getArtistWithSong(EntityManager em, String matchedValue) {

		String jpql="SELECT a.artistName ,album.albumName,s.trackName" +
				" FROM Artist a JOIN a.albums album JOIN album.songs s where s.trackName like ?1 ";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		query.setParameter(1,matchedValue);
		var list=query.getResultList();
		List<String> output = new ArrayList<>();
		for (var row:list){
			String artistName=row[0].toString();
			String albumName=row[1].toString();
			String trackName=row[2].toString();
			output.add("Artist Name: "+artistName+" Album Name: "+albumName+" Song Title: "+trackName);
		}
		return output;
	}


	private static List<Object[]> matchSongBuilder(EntityManager em,String matchedValue) {
		CriteriaBuilder builder=em.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		Root<Artist> root= criteriaQuery.from(Artist.class);
		Join<Artist,Album> joinAlbum=root.join("albums", JoinType.INNER);
		Join<Album,Song> joinSong=joinAlbum.join("songs",JoinType.INNER);
		criteriaQuery
				.multiselect(root.get("artistName"), joinAlbum.get("albumName"), joinSong.get("trackName"))
				.where(builder.like(joinSong.get("trackName"), matchedValue))
				.orderBy(builder.asc(root.get("artistName")));

		return em.createQuery(criteriaQuery).getResultList();
	}



}
