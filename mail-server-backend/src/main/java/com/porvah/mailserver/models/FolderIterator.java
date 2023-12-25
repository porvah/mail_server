package com.porvah.mailserver.models;
import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;
import java.util.List;
public class FolderIterator<T extends ROMail> {
    private final int pageSize;
    private final MailFolder<T> folder;
    private int currentPage = 1;
    public FolderIterator(MailFolder<T> mailList, int pageSize){
        this.folder = mailList;
        this.pageSize = pageSize;
    }
    public int getTotalPages(){
        if(this.folder.mails.isEmpty()) return 1;
        int count = this.folder.mails.size() / this.pageSize;
        return (this.folder.mails.size() % this.pageSize == 0)? count : count+1;
    }
    public int getCurrentPage(){
        return this.currentPage;
    }
    private boolean hasNext(){
        return this.getTotalPages() > this.currentPage;
    }
    private List<T> getPageMails(SortType sort){
        if (this.currentPage == this.getTotalPages())
            return this.folder.getMails(sort).subList((this.currentPage-1)*this.pageSize, this.folder.mails.size());
        else
            return this.folder.getMails(sort).subList((this.currentPage-1)*this.pageSize,
                this.currentPage*this.pageSize);
    }
    public List<T> reset(SortType sort){
        this.currentPage = 1;
        return this.getPageMails(sort);
    }
    public List<T> getNext(SortType sort){
        if(this.hasNext())
            this.currentPage++;
        return this.getPageMails(sort);
    }
    public List<T> getPrev(SortType sort) {
        if (this.currentPage != 1)
            this.currentPage--;
        return this.getPageMails(sort);
    }
}
