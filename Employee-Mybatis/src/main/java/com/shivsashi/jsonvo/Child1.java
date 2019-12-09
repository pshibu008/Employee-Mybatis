package com.shivsashi.jsonvo;

import java.util.ArrayList;

public class Child1
{
	private String				name;

	private ArrayList<Child2>	children;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Child2> getChild2()
	{
		return children;
	}

	public void setChild2(ArrayList<Child2> children)
	{
		this.children = children;
	}
}
