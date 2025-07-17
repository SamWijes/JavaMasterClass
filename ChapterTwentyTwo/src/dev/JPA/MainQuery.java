package dev.JPA;

import dev.JPA.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.dialect.StructAttributeValues;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainQuery {
	public static void main(String[] args) {
		List<Artist> artists = null;
		try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev.JPA.music");
			 EntityManager em = emf.createEntityManager()) {
			var transaction = em.getTransaction();  //wrapping in a transaction
			transaction.begin();
			artists = getArtistJPQL(em, "%Greatest Hits%");

			artists.forEach(System.out::println);

			System.out.println("----------------------------------");
			Stream<Artist> sartists = getArtistsSQL(em, "Bl%");

			var map = sartists
					.limit(10)
							.collect(Collectors.toMap(Artist::getArtistName, a->a.getAlbums().size(),
									Integer::sum, TreeMap::new));

			map.forEach((k, v) -> System.out.println(k + ":" + v));

//			var mapAtAlbumCnt = sartists
//					.limit(10)
//					.collect(Collectors.groupingBy((a) -> a.getAlbums().size(),
//							Collectors.flatMapping(l -> l.getAlbums().stream(), Collectors.toList())));



			//include order
//			var map=sartists
//					.sorted(Comparator.comparing(a->a.getAlbums().size()))
//					.collect(Collectors.groupingBy(a->a.getAlbums().size(),()->new LinkedHashMap<>(),
//							Collectors.mapping(a->a.getArtistName(),Collectors.toList())));



//			linkmap.forEach((k, v) -> System.out.println(k + ":" + v));
//			mapAtAlbumCnt.forEach((k, v) -> System.out.println(k + ":" + v));

//			var names = getArtistNames(em, "%Stev%");
//			names.forEach(System.out::println);


//			names
//					.map(a -> new Artist(
//							a.get("id", Integer.class),(String) a.get("name")))
//							.forEach(System.out::println);


			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();

		}


	}

	private static List<Artist> getArtistJPQL(EntityManager em, String matchedValue) {
//		String jpql="SELECT a FROM Artist a";
//		String jpql="SELECT a FROM Artist a WHERE a.artistName LIKE :partialName";
//		String jpql="SELECT a FROM Artist a WHERE a.artistName LIKE ?1";
		String jpql = "SELECT a FROM Artist a JOIN a.albums album " +
				"WHERE album.albumName LIKE ?1 OR album.albumName LIKE ?2";
		var query = em.createQuery(jpql, Artist.class);
		query.setParameter(1, matchedValue);
		query.setParameter(2, "%Best Of%");

		return query.getResultList();

	}


//	private static List<String > getArtistNames(EntityManager em, String matchedValue) {
////		String jpql="SELECT a FROM Artist a WHERE a.artistName LIKE :partialName";
//		String jpql="SELECT a.artistName FROM Artist a WHERE a.artistName LIKE ?1";
//		var query = em.createQuery(jpql, String.class);
//		query.setParameter(1, matchedValue);
//		return query.getResultList();
//
//	}

//	private static List<Tuple> getArtistNames(EntityManager em, String matchedValue) {
//		String jpql="SELECT a.artistId,a.artistName FROM Artist a"+
//				" WHERE a.artistName LIKE ?1";
//		var query = em.createQuery(jpql, Tuple.class);
//		query.setParameter(1, matchedValue);
//		return query.getResultList();
//
//	}

	//Streams
	private static Stream<Tuple> getArtistNames(EntityManager em, String matchedValue) {
		String jpql = "SELECT a.artistId AS id, a.artistName AS name FROM Artist a" +
				" WHERE a.artistName LIKE ?1";
		var query = em.createQuery(jpql, Tuple.class);
		query.setParameter(1, matchedValue);
		return query.getResultStream();

	}

	public static Stream<Artist> getArtistBuilder(EntityManager em, String matchedValue) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Artist> criteriaQuery = builder.createQuery(Artist.class);
		//Query root represents starting point for constructing a query against a dataset
		//represented as an entity class
		Root<Artist> root=criteriaQuery.from(Artist.class);
		//after root define the operation needed
		criteriaQuery
				.select(root)
				.where(builder.like(root.get("artistName"), matchedValue))
				.orderBy(builder.asc(root.get("artistName")));
		return em.createQuery(criteriaQuery).getResultStream();
	}

	private static Stream<Artist> getArtistsSQL(EntityManager em,String matchedValue){
		var query=em.createNativeQuery(
				"SELECT * FROM music.artists where artist_name like ?1", Artist.class);
		query.setParameter(1, matchedValue);

		return query.getResultStream();

	}


}
