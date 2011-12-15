package ini.trakem2.imaging.filters;

import java.util.Map;

import ij.plugin.ContrastEnhancer;
import ij.process.ImageProcessor;

public class EqualizeHistogram implements IFilter
{
	public EqualizeHistogram() {}
	
	public EqualizeHistogram(Map<String,String> params) {}

	@Override
	public ImageProcessor process(ImageProcessor ip) {
		new ContrastEnhancer().equalize(ip);
		return ip;
	}

	@Override
	public String toXML(String indent) {
		return new StringBuilder(indent)
			.append("<t2_filter class=\"").append(getClass().getName())
			.append("\" />\n").toString();
	}
}
