package ch.twenty.medlineGraph

/**
 *
 * Straight case class for models
 * Created by amasselo on 7/24/15.
 */
package object models {
  case class PubmedId(value:String) extends AnyVal

  case class LastName(value:String) extends AnyVal
  case class ForeName(value:String) extends AnyVal
  case class Initials(value:String) extends AnyVal

  case class Institution(value:String) extends AnyVal
  case class City(value:String) extends AnyVal
  case class Country(value:String) extends AnyVal

  case class Author(lastName:LastName, foreName:ForeName, initials:Initials, affiliation:Option[AffiliationInfo])

  case class AffiliationInfo(institution: Option[Institution], city: Option[City], country: Option[Country])

  case class Citation(pubmedId: PubmedId, authors:Seq[Author])
}
