package repositories

import javax.inject.{Inject, Singleton}

import models.Test
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by crow1 on 2016/11/21.
  */
@Singleton
class TestRepository @Inject()(databaseConfigProvider: DatabaseConfigProvider)
                              (implicit executionContext: ExecutionContext) {


  private val databaseConfig = databaseConfigProvider.get[JdbcProfile]

  import databaseConfig._

  import driver.api._

  private class TestTable(tag: Tag) extends Table[Test](tag, "test1") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    override def * : ProvenShape[Test] = (id, name) <> ((Test.apply _).tupled, Test.unapply)
  }


  private val test = TableQuery[TestTable]


  def list(): Future[Seq[Test]] = db.run {
    test.result
  }

  def create(name: String): Future[Test] = db.run {
    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
    (test.map(p => p.name)
      // Now define it to return the id, because we want to know what id was generated for the person
      returning test.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((name, id) => Test(id, name))
      // And finally, insert the person into the database
      ) += name
  }

}
