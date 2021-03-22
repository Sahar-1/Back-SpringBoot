package Esprit.PiDev.InterfaceService;

import java.util.List;


import Esprit.PiDev.Entity.Forum;

public interface Forum_Service {

	void Add_Forum(Forum F);
	void Update_Forum(Forum C);
	List<Forum> Retrieve_All_Forum();
	
}
