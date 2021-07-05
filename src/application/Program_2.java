package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program_2 {

	public static void main(String[] args) 
	{
		DepartmentDao departmentDao=DaoFactory.createDepartmentDao();
		
		System.out.println("===TESTE 1:department insert===");
		Department department=new Department(null, "Faxina");
		departmentDao.insert(department);
		System.out.println("Inser��o concluida");
		

	}

}
