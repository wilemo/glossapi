import com.vladsch.flexmark.ast.Document;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTag;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.SimTocExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;
import com.wilemo.glossapi.Utils;

import java.util.*;

public class MD2HTML {

    private static final MutableDataHolder OPTIONS = new MutableDataSet()
           .set(JekyllTagExtension.LIST_INCLUDES_ONLY, false)
            .set(JekyllTagExtension.ENABLE_INLINE_TAGS, true)
            .set(Parser.EXTENSIONS, Arrays.asList(
                    TablesExtension.create(),        // GFM表格扩展
                    JekyllTagExtension.create(),     // Jekyll标签语法扩展，"include"文本包含支持
//                    AnchorLinkExtension.create(),    // 锚点扩展，h1h2...标签带超链接
//                    AsideExtension.create(),
//                    AutolinkExtension.create(),      // 自动连接扩展，将包含http地址转成超链接
//                    EmojiExtension.create(),         // 表情扩展
//                    TaskListExtension.create(),      // 将[ ][X]输出为复选框
//                    InsExtension.create(),           // 启用++下划线__
//                    SuperscriptExtension.create(),   // 上标扩展
                    SimTocExtension.create(),
                    YamlFrontMatterExtension.create()
            ));

    private static final Parser PARSER = Parser.builder(OPTIONS).build();
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    public static void main(String[] args) {
        Node document = PARSER.parse(Utils.readResourceToString("/res/layout.md"));

        // see if document has includes
        if (document instanceof Document) {
            Document doc = (Document) document;
            if (doc.contains(JekyllTagExtension.TAG_LIST)) {
                List<JekyllTag> tagList = JekyllTagExtension.TAG_LIST.getFrom(doc);
//
//                for (JekyllTag tag : tagList) {
//                    String tagName = tag.getTag().toString();
//                    BasedSequence basedSequence = tag.getParameters();
//                    System.out.println(tag.getParameters().toString());
//                    BasedSequence[] basedSequences = basedSequence.split(" ");
//                    System.out.println(Arrays.toString(basedSequences));
//                    System.out.println();
//                }
            }
        }

        String html = RENDERER.render(document);
        System.out.println(html);
        Utils.writeStringToResource("/api.html", html);
    }
}
