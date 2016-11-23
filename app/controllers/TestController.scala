package controllers

import javax.inject.Inject

import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.mvc._
import play.api.i18n._
import repositories.TestRepository

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by crow1 on 2016/11/21.
  */
class TestController @Inject()(testRepository: TestRepository, val messagesApi: MessagesApi)
                              (implicit executionContext: ExecutionContext) extends Controller with I18nSupport {

  /**
    * The mapping for the test form.
    */
  val testForm: Form[CreateTestForm] = Form {
    mapping(
      "name" → nonEmptyText
    )(CreateTestForm.apply)(CreateTestForm.unapply)
  }


  def addTest() = Action.async {

    Future.successful(Ok(""))
  }

}


case class CreateTestForm(name: String)