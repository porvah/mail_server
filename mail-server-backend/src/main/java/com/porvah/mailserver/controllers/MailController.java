package com.porvah.mailserver.controllers;

import com.porvah.mailserver.enums.RequiredPage;
import com.porvah.mailserver.enums.SortType;

import com.porvah.mailserver.models.*;
import com.porvah.mailserver.models.ContactCommands.AddContactCommand;
import com.porvah.mailserver.models.ContactCommands.DeleteContactsCommand;
import com.porvah.mailserver.models.ContactCommands.UpdateContactCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping()
@CrossOrigin()
public class MailController {

    final VerificationProxy verificationProxy = new VerificationProxy();
    final MailMediator mediator = new MailMediator();

    final UserFacade userFacade = new UserFacade();
    @PostMapping("signup")
    public ResponseEntity<?> signUp(@RequestBody Map<String, Object> body){
        try {
            String email = (String) body.get("email");
            String password = (String) body.get("password");
            String name = (String) body.get("name");
            verificationProxy.signUpUser(name, email, password);
            return ResponseEntity.ok("{\"msg\" : \"Account created successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"msg\" : \"Account is already created\"}");
        }
    }
    @PostMapping("login")
    public ResponseEntity<?> logIn(@RequestBody Map<String, Object> body) {
        String email = (String) body.get("email");
        String password = (String) body.get("password");
        try {
            int userId = verificationProxy.loginUser(email, password);
            return ResponseEntity.ok(userId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"msg\" : \"Failed to login.\"}");
        }
    }
    @DeleteMapping("logout")
    public ResponseEntity<?> logOut(@RequestParam("token") int token){
        try {
            verificationProxy.logoutUser(token);
            return ResponseEntity.ok("{\"msg\" : \"Logout is successful\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                    .body("{\"msg\" : \"Account is already logged out\"}");
        }
    }
    @GetMapping("getuser")
    public ResponseEntity<?> getLoggedUser(@RequestParam("id") int id) {
        try {
            return ResponseEntity.ok(UserBase.getInstance().getLoggedUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"msg\" : \"User is not logged in\"}");
        }
    }
    @GetMapping("inbox")
    public ResponseEntity<?> getInbox(@RequestParam("token") int token, @RequestParam("sort") int sort,
                                      @RequestParam("required") int req){
        try {
            return ResponseEntity.ok(mediator.getInbox(token, SortType.values()[sort], RequiredPage.values()[req]));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"Token not authorized\"}");
        }
    }
    @GetMapping("trash")
    public ResponseEntity<?> getTrash(@RequestParam("token") int token, @RequestParam("sort") int sort,
                                      @RequestParam("required") int req){
        try {
            return ResponseEntity.ok(mediator.getTrash(token, SortType.values()[sort], RequiredPage.values()[req]));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"Token not authorized\"}");
        }
    }
    @GetMapping("sent")
    public ResponseEntity<?> getSent(@RequestParam("token") int token, @RequestParam("sort") int sort,
                                     @RequestParam("required") int req){
        try {
            return ResponseEntity.ok(mediator.getSent(token, SortType.values()[sort], RequiredPage.values()[req]));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"Token not authorized\"}");
        }
    }
    @GetMapping("draft")
    public ResponseEntity<?> getDraft(@RequestParam("token") int token, @RequestParam("sort") int sort,
                                      @RequestParam("required") int req){
        try {
            return ResponseEntity.ok(mediator.getDraft(token, SortType.values()[sort], RequiredPage.values()[req]));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"Token not authorized\"}");
        }
    }
    @GetMapping("folders")
    public ResponseEntity<?> getMailFolders(@RequestParam("token") int token){
        try {
            return ResponseEntity.ok(mediator.getFolders(token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"Token not authorized\"}");
        }
    }
    @GetMapping("folderemails")
    public ResponseEntity<?> getFolderEmails(@RequestParam("token") int token,
                                             @RequestParam("foldername") String foldername,
                                             @RequestParam("sort") int sort, @RequestParam("required") int req){
        try {
            return ResponseEntity.ok(mediator.getFolderEmails(token, foldername, SortType.values()[sort],
                    RequiredPage.values()[req]));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"Token not authorized\"}");
        }
    }
    @PostMapping("createfolder")
    public ResponseEntity<?> createFolder(@RequestBody Map<String, Object> body){
        try {
            int token = (int) body.get("token");
            String folderName = (String) body.get("foldername");
            mediator.createFolder(token, folderName);
            return ResponseEntity.ok("{\"msg\" : \"Folder is created successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"msg\" : \"Unauthorized token error\"}");
        }
    }
    @DeleteMapping("deletefolder")
    public ResponseEntity<?> deleteFolder(@RequestBody Map<String, Object> body){
        try {
            int token = (int) body.get("token");
            String folderName = (String) body.get("foldername");
            mediator.deleteFolder(token, folderName);
            return ResponseEntity.ok("{\"msg\" : \"Folder is deleted successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"msg\" : \"Unauthorized token error\"}");
        }
    }
    @PostMapping("sendemail")
    public ResponseEntity<?> sendEmail(@RequestParam("token") int token,
                                       @RequestParam("files") Optional<List<MultipartFile>> files,
                                       @RequestParam("receiver") List<String> receiverEmails,
                                       @RequestParam("subject") String subject,
                                       @RequestParam("body") String description,
                                       @RequestParam("priority") int priority
                                       ){
        try {
            List<MultipartFile> newList = new ArrayList<>();
            if (files.isPresent()) {
                newList = files.get();
            }
            mediator.sendEmail(token, receiverEmails, subject, description, priority, newList);
            return ResponseEntity.ok().body("{\"msg\" : \"Email Sent Successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"msg\" : \"User not found\"}");
        }
    }
    @GetMapping("getattachment")
    public ResponseEntity<?> getAttachment(@RequestParam("token") int token, @RequestParam("id") int id) {
        //System.out.println(token);
        try {
            List<Map<String, Object>> attachment = mediator.getAttachment(token, id);

            return ResponseEntity.ok(attachment);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"msg\" : \"error \"}"+ e.getMessage());
        }
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteEmail(@RequestBody Map<String, Object> body){
        try{
            int token = (int) body.get("token");
            List<Integer> id = (List<Integer>) body.get("id");
            mediator.deleteEmail(token, id);
            return ResponseEntity.ok().body("{\"msg\" : \"Email deleted successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"msg\" : \"Unexpected error\"}"+ e.getMessage());
        }
    }
    @PutMapping("restore")
    public  ResponseEntity<?> restoreEmail(@RequestBody Map<String, Object> body) {
        try{
            int token = (int) body.get("token");
            List<Integer> id = (List<Integer>) body.get("id");
            mediator.restoreEmail(token, id);
            return ResponseEntity.ok().body("{\"msg\" : \"Email restored successfully\"}");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"msg\" : \"Unexpected error\"");
        }
    }
    @PostMapping("draftemail")
    public ResponseEntity<?> draftEmail(@RequestParam("token") int token,
                                        @RequestParam("files") Optional<List<MultipartFile>> files,
                                        @RequestParam("subject") String subject,
                                        @RequestParam("body") String description,
                                        @RequestParam("priority") int priority){
        try {
            List<MultipartFile> newList = new ArrayList<>();
            if (files.isPresent()) {
                newList = files.get();
            }
            mediator.draftEmail(token, subject, description, priority, newList);
            return ResponseEntity.ok().body("{\"msg\" : \"Email drafted successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"msg\" : \"Unexpected error\"}");
        }
    }
    @PutMapping("updatedraft")
    public ResponseEntity<?> updateDraft(@RequestParam("id") int id,
                                         @RequestParam("token") int token,
                                         @RequestParam("files") Optional<List<MultipartFile>> files,
                                         @RequestParam("subject") String subject,
                                         @RequestParam("body") String description,
                                         @RequestParam("priority") int priority){
        try {
            List<MultipartFile> newList = new ArrayList<>();
            if (files.isPresent()) {
                newList = files.get();
            }
            mediator.updateDraft(id, token, subject, description, priority, newList);
            return ResponseEntity.ok().body("{\"msg\" : \"Email updated successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"msg\" : \"Unexpected error\"}");
        }
    }
    @PostMapping("submitdraft")
    public ResponseEntity<?> submitDraft(@RequestParam("id") int id,
                                         @RequestParam("token") int token,
                                         @RequestParam("receiver") List<String> receiverEmails,
                                         @RequestParam("files") Optional<List<MultipartFile>> files,
                                         @RequestParam("subject") String subject,
                                         @RequestParam("body") String description,
                                         @RequestParam("priority") int priority){
        try {
            List<MultipartFile> newList = new ArrayList<>();
            if (files.isPresent()) {
                newList = files.get();
            }
            mediator.submitDraft(id, token, receiverEmails,subject, description, priority, newList);
            return ResponseEntity.ok().body("{\"msg\" : \"Drafted Email submitted successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"msg\" : \"Unexpected error\"}");
        }
    }
    @PutMapping("movemails")
    public ResponseEntity<?>  moveMails(@RequestBody Map<String, Object> body){
        try{
            List<Integer> ids = (List<Integer>) body.get("id");
            int token = (int) body.get("token");
            String folderName = (String) body.get("foldername");
            mediator.moveMails(token, ids, folderName);
            return ResponseEntity.ok("{\"msg\" : \"Mails were moved successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"msg\" : \"Unexpected error\"}");
        }
    }
    @GetMapping("contacts")
    public ResponseEntity<?> getContacts(@RequestParam("token") int token, @RequestParam("sort") int sort){
        try{
            UserData userData = this.userFacade.getUserDataByToken(token);
            List<Contact> contacts = new ArrayList<>( userData.getContacts().values());
            if(sort == 0) Collections.reverse(contacts);
            return ResponseEntity.ok(contacts);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"msg\" : \"Unexpected error\"}");
        }
    }

    @PostMapping("createcontact")
    public ResponseEntity<?> addContact(@RequestBody Map<String, Object> body){
        try{
            int token = (int) body.get("token");
            String name = (String) body.get("name");
            ArrayList<String> emails = (ArrayList<String>) body.get("emails");
            AddContactCommand addContactCommand = new AddContactCommand(name, emails);
            UserData userData = this.userFacade.getUserDataByToken(token);
            int contactId = userData.getContactCommandInvoker().executeCommand(addContactCommand);
            return ResponseEntity.ok("{\"msg\" : \"Contact created successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"msg\" : \"Unexpected error\"}");
        }
    }
    @PutMapping("updatecontact")
    public ResponseEntity<?> updateContact(@RequestBody Map<String, Object> body){
        try{
            int token = (int) body.get("token");
            int contactId = (int) body.get("contactId");
            String name = (String) body.get("name");
            ArrayList<String> emails = (ArrayList<String>) body.get("emails");
            UpdateContactCommand updateContactCommand = new UpdateContactCommand(contactId, name, emails);
            UserData userData = this.userFacade.getUserDataByToken(token);
            int updatedContactId = userData.getContactCommandInvoker().executeCommand(updateContactCommand);
            return ResponseEntity.ok("{\"msg\" : \"Contact with id "
                    + updatedContactId + " updated successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"msg\" : \"Unexpected error\"}");
        }
    }
    @DeleteMapping("deletecontacts")
    public ResponseEntity<?> deleteContacts(@RequestBody Map<String, Object> body){
        try{
            int token = (int) body.get("token");
            ArrayList<Integer> contactIds = (ArrayList<Integer>) body.get("contactIds");
            DeleteContactsCommand deleteContactsCommand = new DeleteContactsCommand(contactIds);
            UserData userData = this.userFacade.getUserDataByToken(token);
            userData.getContactCommandInvoker().executeCommand(deleteContactsCommand);
            return ResponseEntity.ok("{\"msg\" : \"Contacts deleted successfully\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"msg\" : \"Unexpected error\"}");
        }
    }
}
