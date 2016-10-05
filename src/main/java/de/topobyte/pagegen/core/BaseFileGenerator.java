package de.topobyte.pagegen.core;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.topobyte.jsoup.ContentGeneratable;
import de.topobyte.jsoup.ElementBuilder;
import de.topobyte.jsoup.HtmlBuilder;
import de.topobyte.jsoup.nodes.Element;

public class BaseFileGenerator implements ContentGeneratable, LinkResolver
{

	protected Context context;
	protected Path file;
	protected Path parentFile;
	protected boolean isDir;
	protected HtmlBuilder builder;

	protected Element header;
	protected Element content;
	protected Element footer;

	public BaseFileGenerator(Context context, Path file, boolean isDir)
	{
		this.context = context;
		this.file = file;
		parentFile = file.getParent();
		this.isDir = isDir;
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
	public Path getFile()
	{
		return file;
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

	public String getLink(Path other)
	{
		if (isDir) {
			return file.relativize(other).toString();
		} else if (parentFile == null) {
			return other.toString();
		} else {
			return parentFile.relativize(other).toString();
		}
	}

	@Override
	public String getLinkFromBase(String path)
	{
		return getLinkFromBase(Paths.get(path));
	}

	@Override
	public String getLinkFromBase(Path path)
	{
		return getLink(context.fromBase(path));
	}

}
