package ch.twenty.medlineGraph.parsers

import ch.twenty.medlineGraph.models._

import scala.util.{Failure, Success, Try}
import scala.xml.Node

/**
 * Parses MedlineCitation nodes information into Citation (or failures)
 *
 * @author Alexandre Masselot.
 */
object MedlineCitationXMLParser {
  /**
   * one XML node into a potential Citation
   * @param node
   * @return
   */
  def apply(node: Node): Try[Citation] = {
    try {
      val pmid = node \ "PMID" text
      val authors = for {
        e <- node \ "Article" \ "AuthorList" \ "Author"
      } yield {
          val affiliation = (e \ "AffiliationInfo" \ "Affiliation").map(x => AffiliationInfoParser(x.text)).map(_.get).headOption
          Author(
            LastName(e \ "LastName" text),
            ForeName(e \ "ForeName" text),
            Initials(e \ "Initials" text),
            affiliation
          )
        }
      Success(Citation(PubmedId(pmid), authors))
    } catch {
      case e: Throwable => Failure(e)
    }
  }

  /**
   * an iterator of XML nodes into one of Citation
   * @param it
   * @return
   */
  def apply(it: Iterator[Node]): Iterator[Try[Citation]] = it.map(apply)

  /**
   * transform an iterator of XML nodes into two iterators:
   * * one with all the parsed citations
   * * one with all the parsing failures
   * @param it
   * @return
   */
  def iteratorsCitationFailures(it: Iterator[Node]): (Iterator[Citation], Iterator[Throwable]) = {
    val (itSucc, itFail) = apply(it).partition(_.isSuccess)
    (itSucc.map(_.get), itFail.map(_.failed.get))
  }
}
