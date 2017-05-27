package com.wilemo.glossapi;

import com.vladsch.flexmark.ast.*;
import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.aside.AsideExtension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.SubscriptExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTag;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagBlock;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.ext.spec.example.SpecExampleExtension;
import com.vladsch.flexmark.ext.tables.TableBlock;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.SimTocExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension;
import com.vladsch.flexmark.ext.xwiki.macros.MacroExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.jira.converter.JiraConverterExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.superscript.SuperscriptExtension;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.BasedSequenceImpl;
import com.vladsch.flexmark.util.sequence.CharSubSequence;
import com.vladsch.flexmark.youtrack.converter.YouTrackConverterExtension;

import java.util.Arrays;
import java.util.List;

/**
 * Md Parse
 * Created by qiaoming on 2017/5/25.
 */
public class GA_DEV_1_Md_Parse {

    /*解析配置选项*/
    private static final MutableDataHolder OPTIONS = new MutableDataSet()

            // 解析扩展配置集合
            .set(Parser.EXTENSIONS, Arrays.asList(
                    AbbreviationExtension.create(),     // 缩写扩展
                    AnchorLinkExtension.create(),       // 锚链接扩展
                    AsideExtension.create(),            // 侧边栏扩展
                    AutolinkExtension.create(),         // 自动连接扩展
                    DefinitionExtension.create(),       // 定义扩展
                    EmojiExtension.create(),            // 表情符号扩展
                    FootnoteExtension.create(),         // 脚注扩展
                 // StrikethroughExtension.create(),    // 删除线扩展 ┓冲突只能用一个
                    SubscriptExtension.create(),        // 脚本扩展   ┛冲突只能用一个
                    TaskListExtension.create(),         // 复选框扩展
                    InsExtension.create(),              // 下划线扩展
                    JekyllTagExtension.create(),        // 自定义标签扩展
                    JiraConverterExtension.create(),    // JIRA转换扩展
                    SpecExampleExtension.create(),      // 示例扩展
                    SuperscriptExtension.create(),      // 上标扩展
                    TablesExtension.create(),           // GFM表格扩展
                    TocExtension.create(),              // 目录扩展
                    SimTocExtension.create(),           // 目录扩展
                    TypographicExtension.create(),      // 排版扩展
                    WikiLinkExtension.create(),         // WIKI链接扩展
                    MacroExtension.create(),            // 宏扩展
                    YamlFrontMatterExtension.create(),  // Yaml 前页扩展
                    YouTrackConverterExtension.create() // YouTrack转换扩展
            ))

            .set(JekyllTagExtension.LIST_INCLUDES_ONLY, false);  // 启用自定义标签解析


    public static void main(String[] args) {
        Parser parser = Parser.builder(OPTIONS).build();                        // 构建解析器
        Node document = parser.parse(Utils.readDefaultString());                // 解析Markdown文档
        Document doc = (Document) document;                                     // 获得Document对象
        ReversiblePeekingIterator<Node> nodes = document.getChildIterator();    // 获得所有子节点

        // 迭代所有子节点
        while (nodes.hasNext()) {
            Node node = nodes.next();                                           // 获得子节点对象
            System.out.println(node.getNodeName());                             // 打印节点名称
            // 打印节点抽象语法树[AST, Abstract Syntax Tree]
            System.out.println(node.toAstString(true));

            // 标题节点
            if (node instanceof Heading) {
                Heading heading = (Heading) node;
                System.out.println(heading.getLevel());
                System.out.println(heading.getText());
            }

            // 段落节点
            if (node instanceof Paragraph) {
                Paragraph paragraph = (Paragraph) node;
                System.out.println(paragraph.getContentChars());
            }

//            if (node instanceof BulletList) {
//                BulletList bulletList = (BulletList) node;
//                ReversiblePeekingIterator<Node> bulletListNodes = bulletList.getChildIterator();
//                while (bulletListNodes.hasNext()) {
//                    BulletListItem bulletListItem = (BulletListItem) bulletListNodes.next();
//                    System.out.println(bulletListItem.getNodeName());
//                    System.out.println(bulletListItem.toAstString(true));
//                    ReversiblePeekingIterator<Node> bulletListItemNodes = bulletListItem.getChildIterator();
//                    while (bulletListItemNodes.hasNext()) {
//                        Paragraph paragraph = (Paragraph) bulletListItemNodes.next();
//                        System.out.println(paragraph.getNodeName());
//                        System.out.println(paragraph.toAstString(true));
//                        System.out.println(paragraph.getContentChars());
//                    }
//                }
//            }

            if (node instanceof OrderedList) {
                OrderedList orderedList = (OrderedList) node;
            }



            if (node instanceof TableBlock) {
                TableBlock tableBlock = (TableBlock) node;
                System.out.println(tableBlock.toAstString(true));
            }

            if (node instanceof JekyllTagBlock) {
                System.out.println("==== Jekyll标签解析 begin ====");
                JekyllTagBlock jekyllTagBlock = (JekyllTagBlock) node;
                System.out.println(jekyllTagBlock.getChars());
                System.out.println("==== Jekyll标签解析 end ====\n");
            }

            System.out.println();
        }


        jekyllTagParse(doc);

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
