package com.shivsashi.jsonvo;

import java.util.ArrayList;

public class Child
{
	private String				name;

	private ArrayList<Child1>	children;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Child1> getChild1()
	{
		return children;
	}

	public void setChild1(ArrayList<Child1> children)
	{
		this.children = children;
	}
}
