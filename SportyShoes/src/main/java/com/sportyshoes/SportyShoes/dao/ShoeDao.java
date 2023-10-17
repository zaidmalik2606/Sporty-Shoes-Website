package com.sportyshoes.SportyShoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sportyshoes.SportyShoes.database.GetConnection;
import com.sportyshoes.SportyShoes.model.PurchaseReport;
import com.sportyshoes.SportyShoes.model.Shoe;
import com.sportyshoes.SportyShoes.model.User;

public class ShoeDao {

	GetConnection connection = new GetConnection();

	public List<User> displayUser(){

		String sql= "select * from users";
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection con= null;

		try {
			con= connection.getConnection();
			if(con!=null) {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				List<User> list = new ArrayList<>();
				while(rs.next()) {
					User user = new User();

					user.setName(rs.getString("userName"));
					user.setAge(rs.getString("userAge"));
					user.setGender(rs.getString("userGender"));
					user.setCity(rs.getString("userCity"));

					list.add(user);
				}			

				return list;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public boolean saveShoe(Shoe shoe) {
		String query="insert into shoes(shoeName,shoeModel,shoeSize,shoePrize) values('"+shoe.getName()+"','"+shoe.getModel()+"','"+shoe.getSize()+"','"+shoe.getPrice()+"')";                   

		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection con= null;

		try {
			con= connection.getConnection();
			if(con!=null) {
				ps = con.prepareStatement(query);
				ps.execute();
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Shoe searchShoe(int id) {

		String sql= "select * from shoes where shoeId="+id;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection con= null;
		Shoe shoe = new Shoe();

		try {
			con= connection.getConnection();
			if(con!=null) {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					shoe.setId(rs.getInt("shoeId"));
					shoe.setModel(rs.getString("shoeModel"));
					shoe.setName(rs.getString("shoeName"));
					shoe.setPrice(rs.getString("shoePrize"));
					shoe.setSize(rs.getString("shoeSize"));
				}			

				return shoe;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	public boolean deleteShoe(int id) {

		String sql = "delete from shoes where shoeId = "+id;
		PreparedStatement ps=null;
		Connection con= null;

		try {
			con= connection.getConnection();
			if(con!=null) {
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
				return true;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean updateShoe(Shoe shoe) {
			String query="update shoes set shoeName=?,shoeModel=?,shoeSize=?,shoePrize=? where shoeId = ?";

			ResultSet rs=null;
			PreparedStatement ps=null;
			Connection con= null;

			try {
				con= connection.getConnection();
				if(con!=null) {
					ps = con.prepareStatement(query);
					ps.setString(1, shoe.getName());
					ps.setString(2, shoe.getModel());
					ps.setString(3, shoe.getSize());
					ps.setString(4, shoe.getPrice());
					ps.setInt(5, shoe.getId());
					ps.executeUpdate();
					return true;
		}
			}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<Shoe> displayShoe() {

		String sql= "select * from shoes";
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection con= null;
		List<Shoe> list = new ArrayList<>();

		try {
			con= connection.getConnection();
			if(con!=null) {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					Shoe shoe = new Shoe();
					shoe.setId(rs.getInt("shoeId"));
					shoe.setModel(rs.getString("shoeModel"));
					shoe.setName(rs.getString("shoeName"));
					shoe.setPrice(rs.getString("shoePrize"));
					shoe.setSize(rs.getString("shoeSize"));
					list.add(shoe);
				}			
				return list;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<PurchaseReport> getReport() {

		String sql= "select * from purchasedetail";
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection con= null;
		List<PurchaseReport> list = new ArrayList<>();

		try {
			con= connection.getConnection();
			if(con!=null) {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					PurchaseReport report = new PurchaseReport();

					report.setShoeModel(rs.getString("modelName"));
					report.setDop(rs.getString("date"));
					report.setShoeName(rs.getString("shoeName"));
					report.setShoeSize(rs.getString("size"));
					report.setUserName(rs.getString("user"));

					list.add(report);
				}			
				return list;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
