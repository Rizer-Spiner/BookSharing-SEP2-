package model.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The RequestList class implements Serializable
 * represents a list of all requests in the system.
 * having one instance variable of type ArrayList of Request.
 *
 * @Author Group 4
 */
public class RequestList implements Serializable
{
   private ArrayList<Request> requests;

   /**
    * the constructor initializing the instance varibale by
    * creating an empty list.
    */
   public RequestList()
   {
      this.requests = new ArrayList<>();
   }

   /**
    * The method is returning a list of requests.
    *
    * @return request as being a list
    */
   public ArrayList<Request> getRequests()
   {
      return requests;
   }

   /**
    * Setting the request list
    *
    * @param requests
    */
   public void setRequests(ArrayList<Request> requests)
   {
      this.requests = requests;
   }

   /**
    * Adding a new request to the list by passing it
    * as a parameter and it cannot be null. so the request cannot be empty
    * it should have a borrower , book and a date.
    *
    * @param request
    */
   public void add(Request request)

   {
      if (request == null)
      {
         throw new IllegalStateException();
      }
      else

         requests.add(request);
   }

   /**
    * Removing a specific request from the list
    * by passing the request as parameter.
    *
    * @param request
    */
   public void remove(Request request)
   {

      requests.remove(request);
   }

   /**
    * Remove the request by the index. the index cannot be less than zero
    * as the index of the list starts from zero.
    *
    * @param index
    */
   public void remove(int index)

   {
      if (index < 0)
      {
         throw new IllegalStateException();
      }
      else
      {
         requests.remove(index);
      }

   }

   /**
    * This method return the size of the list
    *
    * @return size as an integer value
    */
   public int size()
   {
      return requests.size();
   }

   public String toString()
   {
      String s = "";
      for (int i = 0; i < requests.size(); i++)
      {
         s += requests.get(i).toString();
      }
      return s;
   }
}
