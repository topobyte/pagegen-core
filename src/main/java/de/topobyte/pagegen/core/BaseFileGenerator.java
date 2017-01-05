package de.topobyte.pagegen.core;

import java.io.IOException;

import de.topobyte.jsoup.ContentGeneratable;
import de.topobyte.jsoup.ElementBuilder;
import de.topobyte.jsoup.HTML;
import de.topobyte.jsoup.HtmlBuilder;
import de.topobyte.jsoup.components.A;
import de.topobyte.jsoup.nodes.Element;
import de.topobyte.webpaths.WebPath;

public class BaseFileGenerator implements ContentGeneratable, LinkResolver
{

	protected Context context;
	protected WebPath path;
	protected HtmlBuilder builder;

	protected Element content;

	public BaseFileGenerator(Context context, WebPath path)
	{
		this.context = context;
		this.path = path;
		builder = new HtmlBuilder();
	}

	@Override
	public void generate() throws IOException
	{
		Element head = builder.getHead();

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
	public Element getContent()
	{
		return content;
	}

	public void setContent(Element content)
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
