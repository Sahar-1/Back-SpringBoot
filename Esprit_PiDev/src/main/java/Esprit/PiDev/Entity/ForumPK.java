package Esprit.PiDev.Entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ForumPK implements Serializable{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private long idUser;
	private long idGarden;
	private long idForum;
	

}
