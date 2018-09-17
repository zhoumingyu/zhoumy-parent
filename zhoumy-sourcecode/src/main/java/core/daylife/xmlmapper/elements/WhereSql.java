package core.daylife.xmlmapper.elements;

import java.util.Iterator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.internal.util.JavaBeansUtil;

public class WhereSql extends AbstractXmlElementGenerator {

	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

		answer.addAttribute(new Attribute("id", "wheres")); //$NON-NLS-1$
		XmlElement whereXml = new XmlElement("where"); //$NON-NLS-1$
		answer.addElement(whereXml);
		context.getCommentGenerator().addComment(answer);

		StringBuilder sb = new StringBuilder();
		sb.append(" "); //$NON-NLS-1$
		Iterator<IntrospectedColumn> iter = introspectedTable.getAllColumns().iterator();
		while (iter.hasNext()) {
			XmlElement ifXml = new XmlElement("if"); //$NON-NLS-1$
			String colname = MyBatis3FormattingUtilities.getSelectListPhrase(iter.next());
			String attrName = JavaBeansUtil.getCamelCaseString(colname, false);
			ifXml.addAttribute(new Attribute("test", "@com.aidou.core.common.utils.Ognl@isNotEmpty(" + attrName + ")")); //$NON-NLS-1$
			whereXml.addElement(ifXml);
			sb.setLength(0);
			sb.append("and " + colname + "= #{" + attrName + "}");
			ifXml.addElement(new TextElement(sb.toString()));
		}
		if (context.getPlugins().sqlMapSelectAllElementGenerated(answer, introspectedTable)) {
			parentElement.addElement(answer);
		}
	}

}
