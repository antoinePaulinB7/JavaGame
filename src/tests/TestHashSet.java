package tests;

import java.util.HashSet;

import physique.Force;

public class TestHashSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<Force> forces = new HashSet<Force>();
		forces.forEach(force -> System.out.println(force));
		Force f1 = new Force("test");
		forces.forEach(force -> f1.additionner(force));
		forces.add(f1);
		forces.forEach(force -> System.out.println(force));
	}

}
