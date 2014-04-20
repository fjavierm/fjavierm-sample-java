package com.wordpress.infow.javaGeneralKnowledge.dataStructure;

public class BinaryTree {

	Node root;

	public void addNode(int key, String value) {
		Node newNode = new Node(key, value);

		if (this.root == null) {
			this.root = newNode;
		} else {
			Node actualNode = this.root;
			Node parent = null;

			while (true) {
				parent = actualNode;

				if (key < actualNode.key) {
					actualNode = actualNode.leftChild;

					if (actualNode == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					actualNode = actualNode.rightChild;

					if (actualNode == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	public void inOrderTraverse(Node actualNode) {
		if (actualNode != null) {
			this.inOrderTraverse(actualNode.leftChild);

			System.out.print(actualNode.toString());

			this.inOrderTraverse(actualNode.rightChild);
		}
	}

	public void preOrderTraverse(Node actualNode) {
		if (actualNode != null) {
			System.out.print(actualNode.toString());

			this.preOrderTraverse(actualNode.leftChild);

			this.preOrderTraverse(actualNode.rightChild);
		}
	}

	public void postOrderTraverse(Node actualNode) {
		if (actualNode != null) {
			this.postOrderTraverse(actualNode.leftChild);

			this.postOrderTraverse(actualNode.rightChild);

			System.out.print(actualNode.toString());
		}
	}

	public Node findNode(int key) {
		Node actualNode = this.root;

		do {
			if (actualNode == null) {
				return null;
			}

			if (key < actualNode.key) {
				actualNode = actualNode.leftChild;
			} else {
				actualNode = actualNode.rightChild;
			}
		} while (actualNode.key != key);

		return actualNode;
	}

	public int size(Node actualNode) {
		if (actualNode == null) {
			return 0;
		}

		return 1 + this.size(actualNode.leftChild) + this.size(actualNode.rightChild);
	}

	public int maxDepth(Node actualNode) {
		if (actualNode == null) {
			return 0;
		}

		int leftDepth = 1 + this.maxDepth(actualNode.leftChild);
		int rightDepth = 1 + this.maxDepth(actualNode.rightChild);

		return (leftDepth > rightDepth ? leftDepth : rightDepth);
	}

	public int minValue(Node actualNode) {
		if (actualNode.leftChild == null) {
			return actualNode.key;
		} else {
			return this.minValue(actualNode.leftChild);
		}
	}
}

class Node {

	int key;
	String value;

	Node leftChild;
	Node rightChild;

	public Node(int key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "(" + this.key + ":" + this.value + ")";
	}

}