package com.shivsashi.jsonvo;

import java.util.ArrayList;

public class RootVO
{
	private String				name;

	private ArrayList<Child>	children;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Child> getChild()
	{
		return children;
	}

	public void setChild(ArrayList<Child> children)
	{
		this.children = children;
	}
}
