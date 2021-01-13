// Copyright 2017 Sebastian Kuerten
//
// This file is part of pagegen-core.
//
// pagegen-core is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// pagegen-core is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with pagegen-core. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.pagegen.core;

import java.io.IOException;

import de.topobyte.jsoup.ContentGeneratable;
import de.topobyte.jsoup.ElementBuilder;
import de.topobyte.jsoup.HTML;
import de.topobyte.jsoup.HtmlBuilder;
import de.topobyte.jsoup.components.A;
import de.topobyte.jsoup.components.Head;
import de.topobyte.jsoup.nodes.Element;
import de.topobyte.webpaths.WebPath;

public class BaseFileGenerator implements ContentGeneratable, LinkResolver
{

	protected WebPath path;
	protected HtmlBuilder builder;

	protected Element<?> content;

	public BaseFileGenerator(WebPath path)
	{
		this.path = path;
		builder = new HtmlBuilder();
	}

	@Override
	public void generate() throws IOException
	{
		Head head = builder.getHead();

		head.appendChild(ElementBuilder.create("meta", "http-equiv",
				"content-type", "content", "text/html; charset=utf-8"));
	}

	@Override
	public WebPath getPath()
	{
		return path;
	}

	@Override
	public HtmlBuilder getBuilder()
	{
		return builder;
	}

	@Override
	public Element<?> getContent()
	{
		return content;
	}

	public void setContent(Element<?> content)
	{
		this.content = content;
	}

	@Override
	public String getLink(WebPath other)
	{
		WebPath relative = path.relativize(other);
		return relative.toString();
	}

	public A a(WebPath other)
	{
		return HTML.a(path.relativize(other).toString());
	}

}
