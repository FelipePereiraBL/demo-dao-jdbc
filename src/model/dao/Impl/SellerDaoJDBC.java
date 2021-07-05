package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao
{
	Connection conn=null;
	
	public SellerDaoJDBC(Connection conn)
	{
		this.conn=conn;
	}
	
	@Override
	public void insert(Seller obj) 
	{
<<<<<<< HEAD
        PreparedStatement st=null;
=======
		PreparedStatement st=null;
>>>>>>> 6017670bd3f4a14e7ba9141d3f169abc2497bd1e
		
		try 
		{
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) 
			{
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next())
				{
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else 
			{
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) 
		{
			throw new DbException(e.getMessage());
		}
		finally 
		{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Seller obj) 
	{
        PreparedStatement st=null;
		
		try
		{
			st=conn.prepareStatement(
					"UPDATE seller "
					+"SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+"WHERE Id = ?",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3,new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());
			
			int rowsAffected=st.executeUpdate();
			
			if(rowsAffected>0)
			{
				st.getGeneratedKeys();
			}
		} 
		catch (SQLException e) 
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
		}    		
	}

	@Override
	public void deleteById(Integer id) 
	{
		
		
	}

	@Override
	public Seller findById(Integer id) 
	{
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try 
		{
			st=conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE seller.Id = ?"			
					);
			
			st.setInt(1, id);
			
			rs=st.executeQuery();
			
			if(rs.next())
			{
				Department dep=instantiateDepartment(rs);
				
				Seller sell=instantiateSeller(rs,dep);
				
				return sell;
				
			}
			return null;
		} 
		catch (SQLException e) 
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
				
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException
	{
		Department dep=new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		
		return dep;
	}
	private Seller instantiateSeller(ResultSet rs,Department dep) throws SQLException
	{
		Seller sell=new Seller();
		sell.setId(rs.getInt("Id"));
		sell.setName(rs.getString("Name"));
		sell.setEmail(rs.getString("Email"));
		sell.setBirthDate(rs.getDate("BirthDate"));
		sell.setBaseSalary(rs.getDouble("BaseSalary"));
		sell.setDepartment(dep);
		
		return sell;
	}

	@Override
	public List<Seller> findAll()
	{
		PreparedStatement st=null;
		ResultSet rs=null;

		try
		{
			st=conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
				    +"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"ORDER BY Name"
					);
			
			rs=st.executeQuery();
			
			List<Seller>list=new ArrayList<Seller>();
			Map<Integer, Department>map=new HashMap<>();
			
			while (rs.next()) 
			{
				Department dep=map.get(rs.getInt("DepartmentId"));
				
				if(dep==null)
				{
					dep=instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);				
				}
				
				Seller sell=instantiateSeller(rs, dep);
				list.add(sell);				
			}
			return list;
			
		} 
		catch (SQLException e) 
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	

	@Override
	public List<Seller> findByDepartment(Department department) {

		PreparedStatement st=null;
		ResultSet rs=null;
		
		try 
		{
			st=conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
				   +"FROM seller INNER JOIN department "
				   +"ON seller.DepartmentId = department.Id "
				   +"WHERE DepartmentId = ? "
				   +"ORDER BY Name");
			
			st.setInt(1, department.getId());
			
			rs=st.executeQuery();
			
			List<Seller>list=new ArrayList<>();
			Map<Integer, Department>map=new HashMap<>();
			
			while(rs.next())
			{
				Department dep=map.get(rs.getInt("DepartmentId"));
				
				if(dep==null)
				{
					dep=instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller sell=instantiateSeller(rs, dep);
				list.add(sell);
			}
			return list;
		} 
		catch (SQLException e) 
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeResultSet(rs);
			DB.closeResultSet(rs);
		}
	}

}
