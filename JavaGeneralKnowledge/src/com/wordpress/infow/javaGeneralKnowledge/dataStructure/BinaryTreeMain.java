package com.wordpress.infow.javaGeneralKnowledge.dataStructure;

public class BinaryTreeMain {

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();

		bt.addNode(50, "Richard I");
		bt.addNode(13, "William I");
		bt.addNode(17, "William II");
		bt.addNode(26, "Stephen");
		bt.addNode(98, "Edward II");
		bt.addNode(45, "Henry II");
		bt.addNode(87, "Edward I");
		bt.addNode(23, "Henry I");
		bt.addNode(54, "John");
		bt.addNode(65, "Henry III");

		System.out.print("In Order:");
		bt.inOrderTraverse(bt.root);
		System.out.println();

		System.out.print("Pre Order:");
		bt.preOrderTraverse(bt.root);
		System.out.println();

		System.out.print("PostOrder:");
		bt.postOrderTraverse(bt.root);
		System.out.println();

		System.out.println("Exists 87? " + bt.findNode(87).toString());

		System.out.println("Tree size:" + bt.size(bt.root));

		System.out.println("Tree depth: " + bt.maxDepth(bt.root));

		System.out.println("Min value: " + bt.minValue(bt.root));
	}
}
