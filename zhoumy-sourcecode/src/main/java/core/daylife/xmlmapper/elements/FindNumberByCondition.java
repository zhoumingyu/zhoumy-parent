package core.daylife.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
public class FindNumberByCondition extends AbstractXmlElementGenerator {

	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

		answer.addAttribute(new Attribute("id", "findNumberByCondition")); //$NON-NLS-1$
		answer.addAttribute(new Attribute("resultType", //$NON-NLS-1$
				"int"));

		context.getCommentGenerator().addComment(answer);

		StringBuilder sb = new StringBuilder();
		sb.append("select count(*)"); //$NON-NLS-1$

		if (sb.length() > 0) {
			answer.addElement(new TextElement(sb.toString()));
		}

		sb.setLength(0);
		sb.append("from "); //$NON-NLS-1$
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
		answer.addElement(new TextElement(sb.toString()));

		if (context.getPlugins().sqlMapSelectAllElementGenerated(answer, introspectedTable)) {
			parentElement.addElement(answer);
		}
	}

}
