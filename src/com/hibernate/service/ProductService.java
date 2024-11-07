package com.hibernate.service;

import java.util.List;
import java.util.Scanner;

import com.hibernate.exception.InvalidIdException;
import com.hibernate.model.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ProductService {

	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	public ProductService(EntityManager entityManager, EntityTransaction entityTransaction) {
		this.entityManager = entityManager;
		this.entityTransaction = entityTransaction;
	}


	public Product takeInput(Scanner sc) {
		Product product = new Product();
		System.out.println("Enter Product Title");
		sc.nextLine();
		product.setTitle(sc.nextLine());
		System.out.println("Enter description");
		product.setDescription(sc.nextLine());
		System.out.println("Enter price");
		product.setPrice(sc.nextDouble());
		return product;
	}
	
	public void insert(Product product) {
		entityTransaction.begin();
		entityManager.persist(product);
		entityTransaction.commit();
	}


	public Product getById(Scanner sc) throws InvalidIdException {
		entityTransaction.begin();
		System.out.println("Enter product id"); 
		int id = sc.nextInt();
		Product product = entityManager.find(Product.class, id); 
		if(product == null)
			throw new InvalidIdException("Product ID Inv...");
		entityTransaction.commit();
		return product;
		
	}
	
	public void delete(Product product) {
		entityTransaction.begin();
		entityManager.remove(product); 
		entityTransaction.commit();
	}


	public List<Product> getAll() {
		// TODO Auto-generated method stub
		entityTransaction.begin();
		String jpql = "select * from Product p";
		TypedQuery<Product> TypedQuery = entityManager.createQuery(jpql, Product.class);
		List<Product> list = TypedQuery.getResultList();
		entityTransaction.commit();
		return list;
	}
	
	
}