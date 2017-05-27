import com.vladsch.flexmark.ast.*;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTag;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagBlock;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.ext.tables.TableBlock;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;
import com.wilemo.glossapi.Utils;

import java.util.Arrays;
import java.util.List;

public class Mark_1_Parse {

    private static final MutableDataHolder OPTIONS = new MutableDataSet()
            .set(JekyllTagExtension.LIST_INCLUDES_ONLY, false)

            .set(Parser.EXTENSIONS, Arrays.asList(
                    TablesExtension.create(),
                    JekyllTagExtension.create()
            ));

    public static void main(String[] args) {
        Parser parser = Parser.builder(OPTIONS).build();
        Node document = parser.parse(Utils.readResourceToString("res/test.md"));
        Document doc = (Document) document;

        jekyllTagParse(doc);

        ReversiblePeekingIterator<Node> nodes = document.getChildIterator();
        while (nodes.hasNext()) {

            Node node = nodes.next();
            System.out.println(node.getNodeName());
            System.out.println(node.toAstString(true));

            if (node instanceof Heading) {
                System.out.println("----");
                Heading heading = (Heading) node;
                System.out.println(heading.getLevel());
                System.out.println(heading.getText());
            }

            if (node instanceof BulletList) {
                System.out.println("----");
                BulletList bulletList = (BulletList) node;
                ReversiblePeekingIterator<Node> bulletListNodes = bulletList.getChildIterator();
                while (bulletListNodes.hasNext()) {
                    BulletListItem bulletListItem = (BulletListItem) bulletListNodes.next();
                    System.out.println(bulletListItem.getNodeName());
                    System.out.println(bulletListItem.toAstString(true));
                    ReversiblePeekingIterator<Node> bulletListItemNodes = bulletListItem.getChildIterator();
                    while (bulletListItemNodes.hasNext()) {
                        Paragraph paragraph = (Paragraph) bulletListItemNodes.next();
                        System.out.println(paragraph.getNodeName());
                        System.out.println(paragraph.toAstString(true));
                        System.out.println(paragraph.getContentChars());
                    }
                }
            }

            if (node instanceof OrderedList) {
                OrderedList orderedList = (OrderedList) node;
            }

            if (node instanceof Paragraph) {
                Paragraph paragraph = (Paragraph) node;
                System.out.println("text: " + paragraph.getContentChars());
            }

            if (node instanceof TableBlock) {
                TableBlock tableBlock = (TableBlock) node;
                System.out.println(tableBlock.toAstString(true));
            }

            if (node instanceof JekyllTagBlock) {
                System.out.println("==== Jekyll标签解析 begin ====");
                JekyllTagBlock jekyllTagBlock = (JekyllTagBlock) node;
                System.out.println( jekyllTagBlock.getChars());
                System.out.println("==== Jekyll标签解析 end ====\n");
            }

            System.out.println();
        }
    }

    /**
     * Jekyll 标签解析
     *
     * @param document 文档对象
     */
    private static void jekyllTagParse(Document document) {
        System.out.println("==== Jekyll标签解析 begin ====");
        if (document.contains(JekyllTagExtension.TAG_LIST)) {
            List<JekyllTag> tagList = JekyllTagExtension.TAG_LIST.getFrom(document);
            System.out.println("找到" + tagList.size() + "个Jekyll标签解析");
            for (JekyllTag tag : tagList) {
                System.out.println("------");
                String tagName = tag.getTag().toString();
                System.out.println("标签名称：" + tagName);
                System.out.println("标签参数：" + Arrays.toString(tag.getParameters().split(" ")));
            }
        }
        System.out.println("==== Jekyll标签解析 end ====\n");
    }
}
