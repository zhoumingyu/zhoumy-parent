package core.daylife.xmlmapper.elements;

import java.util.Iterator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class FindPageBreakByCondition extends AbstractXmlElementGenerator {

	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

		answer.addAttribute(new Attribute("id", "findPageBreakByCondition")); //$NON-NLS-1$
		answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
				"BaseResultMap"));
		answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
				"map"));
		context.getCommentGenerator().addComment(answer);

		StringBuilder sb = new StringBuilder();
		sb.append("select "); //$NON-NLS-1$
		Iterator<IntrospectedColumn> iter = introspectedTable.getAllColumns().iterator();
		while (iter.hasNext()) {
			sb.append(MyBatis3FormattingUtilities.getSelectListPhrase(iter.next()));

			if (iter.hasNext()) {
				sb.append(", "); //$NON-NLS-1$
			}

			if (sb.length() > 80) {
				answer.addElement(new TextElement(sb.toString()));
				sb.setLength(0);
			}
		}

		if (sb.length() > 0) {
			answer.addElement(new TextElement(sb.toString()));
		}

		sb.setLength(0);
		sb.append("from "); //$NON-NLS-1$
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
		answer.addElement(new TextElement(sb.toString()));

		sb.setLength(0);
		sb.append("order by id"); //$NON-NLS-1$
		answer.addElement(new TextElement(sb.toString()));

		if (context.getPlugins().sqlMapSelectAllElementGenerated(answer, introspectedTable)) {
			parentElement.addElement(answer);
		}
	}

}
