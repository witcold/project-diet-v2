package com.dataart.spring.analytics;

/**
 * @author Witold Mescheryakov
 *
 */
public class Diet {

/*	public static int calculateBMR(User u) {
		int h = u.getCurrentHeight();
		float w = u.getCurrentWeight();
		int a = 2014 - u.getYearOfBirth();
		boolean s = (u.getSex() == Sex.Male);
		float[] list = new float[5];
		
		list[0] = BMR.calculateHB(s, h, w, a);
		list[1] = BMR.calculateHBR(s, h, w, a);
		list[2] = BMR.calculateM(s, h, w, a);
		list[3] = BMR.calculateKM(s, h, w, a);
		list[4] = BMR.calculateC(s, h, w, a);
		Arrays.sort(list);
		return (int) list[2];
	}*/

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
