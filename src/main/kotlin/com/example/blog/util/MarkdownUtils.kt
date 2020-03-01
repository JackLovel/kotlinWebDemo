package com.example.blog.util

import org.commonmark.ext.gfm.tables.TableBlock
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension
import org.commonmark.node.Link
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.AttributeProvider
import org.commonmark.renderer.html.HtmlRenderer
import java.util.*


/**
 * Created by limi on 2017/10/22.
 */
object MarkdownUtils {
    /**
     * markdown格式转换成HTML格式
     * @param markdown
     * @return
     */
    fun markdownToHtml(markdown: String?): String {
        val parser = Parser.builder().build()
        val document = parser.parse(markdown)
        val renderer = HtmlRenderer.builder().build()
        return renderer.render(document)
    }

    /**
     * 增加扩展[标题锚点，表格生成]
     * Markdown转换成HTML
     * @param markdown
     * @return
     */
    @JvmStatic
    fun markdownToHtmlExtensions(markdown: String?): String { //h标题生成id
        val headingAnchorExtensions = setOf(HeadingAnchorExtension.create())
        //转换table的HTML
        val tableExtension = Arrays.asList(TablesExtension.create())
        val parser = Parser.builder()
                .extensions(tableExtension)
                .build()
        val document = parser.parse(markdown)
        val renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions)
                .extensions(tableExtension)
                .attributeProviderFactory { CustomAttributeProvider() }
                .build()
        return renderer.render(document)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val table = "| hello | hi   | 哈哈哈   |\n" +
                "| ----- | ---- | ----- |\n" +
                "| 斯维尔多  | 士大夫  | f啊    |\n" +
                "| 阿什顿发  | 非固定杆 | 撒阿什顿发 |\n" +
                "\n"
        val a = "[imCoding 爱编程](http://www.lirenmi.cn)"
        println(markdownToHtmlExtensions(a))
    }

    /**
     * 处理标签的属性
     */
    internal class CustomAttributeProvider : AttributeProvider {
        override fun setAttributes(node: Node, tagName: String, attributes: MutableMap<String, String>) { //改变a标签的target属性为_blank
            if (node is Link) {
                attributes["target"] = "_blank"
            }
            if (node is TableBlock) {
                attributes["class"] = "ui celled table"
            }
        }
    }
}