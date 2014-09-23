package com.dataart.spring.analytics;

/**
 * @author Witold Mescheryakov
 *
 */
public class Diet {

/*	public static void method(User u) {
		int daily = (int) (calculateBMR(u) * 1.7);
		try {
			Connection c = null;//ConnectionPool.getInstance().getConnection();
			if (c != null) {
				PreparedStatement ps = c.prepareStatement(
						"SELECT D.Date, SUM(M.Energy * D.Weight * 10)"
						+ "FROM Diet.Diaries AS D INNER JOIN Diet.Meals AS M ON (M.Id = D.MealId)"
						+ "WHERE (D.UserId = ?)"
						+ "GROUP BY D.Date;"
				);
				ps.setLong(1, u.getId());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int current = rs.getInt(2);
					if (current > daily) {
						System.out.println(">");
					}
				}
				ps.close();
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

}
