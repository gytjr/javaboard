package java_board.user.model;



import java.util.ArrayList;



public class UserDao {

	private ArrayList<User> users = new ArrayList<User>();



	public ArrayList<User> getUsers() {

		return this.users;

	}

	

	public User getUserById(String userId) {

		for (int i = 0; i < users.size(); i++) {

			if (userId.equals(users.get(i).getUserId())) {

				return users.get(i);

			}

		}



		return null;

	}

}