package org.cometd.bayeux.client;


import java.io.IOException;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.Session;



/**
 * Client Bayeux Session.
 * 
 * This interface represents the client side Bayeux session, which is
 * the entity that can subscribe and publish to channels. 
 * 
 */
public interface ClientSession extends Session
{
    /* ------------------------------------------------------------ */
    /** Add and extension to this session.
     * @param extension
     */
    void addExtension(Extension extension);

    /**
     * <p>Initiates the bayeux protocol handshake with the server(s).</p>
     * @throws IOException if a handshake fails
     */
    void handshake() throws IOException;

    /* ------------------------------------------------------------ */
    /**
     * Get a channel scoped by this session.
     * <p>
     * Get a channel representation scoped to this session.
     * The SessionChannel may be for a specific channel (eg /foo/bar)
     * or for a wild channel (eg /meta/** or /foo/* ).
     * @param channelName absolute or wild channel name. 
     * @return a channel scoped by this session.
     */
    SessionChannel getChannel(String channelName);
    
    /* ------------------------------------------------------------ */
    /* ------------------------------------------------------------ */
    /**
     * <p>Extension API for client session.</p>
     *
     * @see ClientSession#addExtension(Extension)
     */
    public interface Extension
    {
        /**
         * Callback method invoked every time a normal message is incoming.
         * @param session the session object
         * @param message the incoming message
         * @return true if message processing should continue, false if it should stop
         */
        boolean rcv(ClientSession session, Message.Mutable message);

        /**
         * Callback method invoked every time a meta message is incoming.
         * @param session the session object
         * @param message the incoming meta message
         * @return true if message processing should continue, false if it should stop
         */
        boolean rcvMeta(ClientSession session, Message.Mutable message);

        /**
         * Callback method invoked every time a normal message is outgoing.
         * @param session the session object
         * @param message the outgoing message
         * @return true if message processing should continue, false if it should stop
         */
        boolean send(ClientSession session, Message.Mutable message);

        /**
         * Callback method invoked every time a meta message is outgoing.
         * @param session the session object
         * @param message the outgoing meta message
         * @return true if message processing should continue, false if it should stop
         */
        boolean sendMeta(ClientSession session, Message.Mutable message);
    }
    
}
