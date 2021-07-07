package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class Program_2 {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		DepartmentDao departmentDao=DaoFactory.createDepartmentDao();
		
		/*System.out.println("===TESTE 1:department insert===");
		Department department=new Department(null, "Faxina");
		departmentDao.insert(department);
		System.out.println("Inserção concluida");
		
		System.out.println("\n===TESTE 2:department update===");
		department=departmentDao.findById(6);
		department.setName("Pintura");
		departmentDao.update(department);
		System.out.println("Update conpleted");*/
		
		System.out.println("===TESTE 3:department delete===");
		System.out.print("\nQuantas linhas vai deletar:");
		int n=sc.nextInt();
		
		for (int i = 0; i<n; i++) 
		{
			System.out.print("Digite o id:");
			int id=sc.nextInt();
			departmentDao.deleteById(id);
		}        
        
        sc.close();

	}

}
