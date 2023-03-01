//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    UrgentCareAdmissions
// Course:   CS 300 Spring 2023
//
// Author:   Emili Robles
// Email:    ejrobles@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    n/a
// Partner Email:   n/a
// Partner Lecturer's Name: n/a
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         Peer Mentors, helped on class methods - getAdmissionIndex
// check if list is full and corrected for loop if statement for index , size
// also helped on addPatient- helped me understand how to shift elements
// Online Sources:  Zybook ch 1 and 2 helped setting up for loops for removePatient, addPatient,
// and getPatientIndex. Stack Overflow method classes helped develop getSummary method. 
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models the different levels of triages, with RED being the most
 * urgent, YELLOW being in the middle and GREEN being lowest for patients 
 */
public class UrgentCareAdmissions {
  public static final int RED = 0;
  public static final int YELLOW = 1;
  public static final int GREEN = 2;
  /**
   * getAdmissionsIndex method helps people when they come in the hospital and
   * places them at a triage level by indicating what index they belong in due
   * to the size.
   * It also checks if the patientsList is full, and if it is then it returns -1
   * to indicate there's no space for the patient.
   * 
   * @param triage RED, YELLOW, GREEN level
   * @param patientsList active list of patient records
   * @param size # of patients in the list
   * @return index at which the patient should be inserted into the list
   * or -1 if the list is full
   */
  public static int getAdmissionIndex(int triage, int[][] patientsList, int size) {
    if(size == patientsList.length) { //list is full
      return -1;
    }
    int index;
    for (index = 0; index < size; index++) { //goes through list for levels
      if (patientsList[index][2] > triage) {
        break;
      }
    }
    return index;
  }
  
  /**
   * addPatient first checks if index is valid or not to continue into adding,
   * to add new patient, we first shift all elements down to make room for the given
   * index. Then after adding patient, we update the new size of it in the patient Record.
   * 
   * @param patientRecord is an arrayList of 3 elements = 5 digit case ID, 
   * admission order #, triage level
   * @param index - index where patientRecord should be added (getAdmission Index needs to work first)
   * @param  patientsList - active list of patients records
   * @param size - # of patients in the list
   * @return size - # of patients in the list
   */
  public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
   
    if(index < 0 || index > size || size == patientsList.length) {
      return size;
    } //if index isnt valid then return size
    int i;
    for(i = size -1; i >= index; i--) {
      patientsList[i+1] = patientsList[i];
      //if index is valid then shift elements for new patient
    }
    patientsList[index] = patientRecord;
    return size + 1; //adds new patient
      
    }
  /**
   * Once patient is done, their record index # is removed from the patients list
   * and updates the size
   * 
   * @param  patientsList - active list of patients records
   * @param size - # of patients in the list
   * @return # of patients in patientsList
   */
  public static int removeNextPatient(int[][] patientsList, int size) {
    
    if(size == 0) {
      return size; //check if its empty
      
    }
    int i;
    for (i = 0; i< size -1 ; i++){
      patientsList[i] = patientsList[i+1];
      //starts from end to see which to remove
    }
    patientsList[size-1] = null;
    return size -1;
  }
  
  /**
   * Finds index of patients caseID number without changing the patient list.
   * 
   * @param caseID - 5 digit case # assigned to patient
   * @param  patientsList - active list of patients records
   * @param size - # of patients in the list
   * @return index of the patient record matching the give caseID #, or -1 if the list is empty/
   * # is not found
   */
  public static int getPatientIndex(int caseID, int[][] patientsList, int size) {
    
    int i;
    for(i = 0; i < size; i++) {
      if(patientsList[i][0] == caseID) {
        return i; //searches caseID #
      }
      if(size == 0) {
      return -1; //if list is empty
      }
    }
    return -1; //not found
  }
  
  /**
   * Finds smallest index for patient since thats the longest patient waiting. 
   * Also check if list is empty to return -1
   * 
   * 
   * @param  patientsList - active list of patients records
   * @param size - # of patients in the list
   * @return index of the patient record w/ the smallest value in their arrival
   * integer, or -1 if list is empty
   */
  public static int getLongestWaitingPatientIndex(int[][] patientsList, int size) {
    // patientsList - current active list of patient records
    // size - # of patients in the patientsList
    // @returns index of the patient record w/ the smallest value in their arrival integer, or -1 if
    // list is empty
    if(size == 0) {
    return -1;
    }
    int index = 0;
    int i;
    for(i = 1; i < size; i++) {
      if(patientsList[i][1] < patientsList[index][1]) {
        index = i; //for loop to see smallest index
      }
    }
    return index; //found smallest value/s within list
    }
  
  /**
   * Creates string to see the count of each triage level within the patients list.
   * 
   * @param  patientsList - active list of patients records
   * @param size - # of patients in the list
   * @return a string summarizing the patientsList
   * Total number of patients:
   * RED:
   * YELLOW:
   * GREEN:
   */
 
  public static String getSummary(int[][] patientsList, int size) {
  
    int redCount = 0; //counts RED level triage
    int yellowCount = 0; //counts YELLOW level triage
    int greenCount = 0; // counts GREEN level triage
    int i;
    
    if(size == 0 || patientsList == null) {
      String unvalid = "Total number of patients: 0\nRED: 0\nYELLOW: 0\nGREEN: 0";
      return unvalid;
    } 
    
    for(i = 0; i < size; i++) {
      switch(patientsList[i][2]) {
        case 0:
          redCount++;
          break; //patients in RED
        case 1:
          yellowCount++;
          break; //patients in YELLOW
        case 2:
          greenCount++;
          break; //patients in GREEN
      }
    }
    String summary = "Total number of patients: " +size + "\nRED:" + redCount + "YELLOW:\n" + yellowCount + "GREEN:\n" + greenCount + "\n";
    return summary;
    //outputs how many counts are in each triage 
  }
}

