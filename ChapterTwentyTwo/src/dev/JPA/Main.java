package dev.JPA;

import dev.JPA.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		try (var sessionFactory = Persistence.createEntityManagerFactory("dev.JPA.music");
			 EntityManager entityManager = sessionFactory.createEntityManager()) {

			var transaction = entityManager.getTransaction();
			transaction.begin();
//			entityManager.persist(new Artist("Muddy Water"));
//			Artist artist = entityManager.find(Artist.class, 200);
//			Artist artist = new Artist(202, "Muddy Water");  //manually create an entity
//			System.out.println(artist);
//			entityManager.merge(artist);             		 //make the manually created entity managed
//			artist.setArtistName("Muddy Waters");
//			entityManager.remove(artist);
//			artist.removeDuplicates();
//			artist.addAlbum("The Best of Muddy Waters");
//			System.out.println(artist);


			Artist artist = entityManager.find(Artist.class, 200);
			System.out.println(artist);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
