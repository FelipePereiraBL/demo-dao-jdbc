package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program_2 {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		DepartmentDao departmentDao=DaoFactory.createDepartmentDao();
		Department department=new Department();
		
		System.out.println("===TESTE 1:department insert===");
		System.out.print("\nQuantos departamentos quer inserir:");
		int inser=sc.nextInt();
		for (int i = 0; i <inser; i++)
		{
			System.out.print("Nome do novo departamento:");
			String name=sc.next();
			department.setName(name);
			departmentDao.insert(department);
		}
		System.out.println("Inserção concluida");
		
		System.out.println("\n===TESTE 2:department update===");
		System.out.print("\nQuantos departamentos quer atualizar:");
		int atual=sc.nextInt();
		for (int i = 0; i <atual; i++)
		{
			System.out.print("Id do departamento:");
			department=departmentDao.findById(sc.nextInt());
			System.out.print("Novo nome do  departamento:");
			department.setName(sc.next());
			departmentDao.update(department);
		}
		System.out.println("Update conpleted");
		
		System.out.println("\n===TESTE 3:department delete===");
		System.out.print("\nQuantas linhas vai deletar:");
		int n=sc.nextInt();		
		for (int i = 0; i<n; i++) 
		{
			System.out.print("Digite o id:");
			int id=sc.nextInt();
			departmentDao.deleteById(id);
		}        
        
		System.out.println("\n===TESTE 4:department findAll===");
		List<Department>list=departmentDao.findAll();
		for (Department dep : list) 
		{
			System.out.println(dep);
		}
        sc.close();

	}

}
