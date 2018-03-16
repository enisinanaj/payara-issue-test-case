Can't join JDBC transaction with JTA transaction with Hibernate 4/Hibernate 5 on Glassfish 4

I'm trying to move our application forward. Now it runs under Glassfish 3, JAVA EE 6 and uses Hibernate 3 as the JPA implementation.
I wrote an example that shows a problem I've with transactions. In some circumnstances the application needs to manually call the entity manager flush() method. But even in a JTA environment, what happens is that the flush() causes a physical commit on the underlying database (we're using ojdbc6.jar JDBC driver to connect to ORACLE X/XI). This is not the expected behaviour, due to the fact that the JDBC transaction should join the JTA transaction. If after the flush() an exception is raised by the EJB, the data flushed should be rollbacked.
In Hibernate 3 everything works perfectly. In  Hibernate 4.3.5 it doesn't. 

Debugging the Hibernate 4.3 code I found this

package org.hibernate.jpa.internal.EntityManagerImpl

               @Override
               protected Session internalGetSession() {
                              if ( session == null ) {
                                            ...
                                            SessionBuilderImplementor sessionBuilder = internalGetEntityManagerFactory().getSessionFactory().withOptions();
                                            
---->>>>>>          sessionBuilder.autoJoinTransactions( getTransactionType() != PersistenceUnitTransactionType.JTA ); <<<<<<-----
                                            
                                            session = sessionBuilder.openSession();
                              }
                              return session;
               }


This leads the org.hibernate.engine.transaction.internal.TransactionCoordinatorImpl.attemptToRegisterJtaSync() method to entering in this block and skipping the synchronization with the JTA platform..


....
               final JoinStatus joinStatus = currentHibernateTransaction.getJoinStatus();
               if ( joinStatus != JoinStatus.JOINED ) {
                                            // the transaction is not (yet) joined, see if we should join...
---->>>>>            if ( !transactionContext.shouldAutoJoinTransaction() ) {  <<<<<<----
                                                           // we are supposed to not auto join transactions; if the transaction is not marked for join
                                                           // we cannot go any further in attempting to join (register sync).
                                                           if ( joinStatus != JoinStatus.MARKED_FOR_JOINED ) {
                                                                          if (isDebugging) {
                                                                                         LOG.debug( "Skipping JTA sync registration due to auto join checking" );
                                                                          }
                                                                          return;
                                                           }
                                            }
                              }

 
 I tried to debug Hibernate 5 and here the AutoJoinTransaction problem disappeared, most of the transaction code has been rewritten. In any case, the flush() problem is still present. 

  I wrote a small example that demonstrates it. There's a TestClient class to test the behavoiur I described. There are four constants to set if and when the setRollbackOnly method will be called. The problem is visible even whitout the call.
 
 The zip file contains a xls file that summarize the test I've done
 
 
 Any ideas?

 
 Thanks in advance,

 David Obber

 


