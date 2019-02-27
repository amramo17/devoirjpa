package persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// thanks to https://gist.github.com/laminr/5434970
// Getting the EntityManagerFactory from a singleton in the PersistenceManager class
public class PersistenceManager {

	public static final boolean DEBUG = true;

	private static final PersistenceManager singleton = new PersistenceManager();

	protected EntityManagerFactory emf;

	public static PersistenceManager getInstance() {

		return singleton;
	}

	private PersistenceManager() {
	}

	public EntityManagerFactory getEntityManagerFactory() {

		if (emf == null)
			createEntityManagerFactory();
		return emf;
	}

	public void closeEntityManagerFactory() {

		if (emf != null) {
			emf.close();
			emf = null;
			if (DEBUG)
				System.out.println("n*** Persistence finished at " + new java.util.Date());
		}
	}

	protected void createEntityManagerFactory() {

		this.emf = Persistence.createEntityManagerFactory("ibmcloud");
		if (DEBUG)
			System.out.println("n*** Persistence started at " + new java.util.Date());
	}
}