package org.resturant.controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HardInfo {
	private  static final Logger log = LoggerFactory.getLogger(HardInfo.class);
	private static final int DEFAULT_PORT = 12321;
//
//	/**
//	 * Attempts to find a free port
//	 * 
//	 * @throws IOException
//	 * 
//	 * @throws IOException
//	 */
//	public static int findFreePort() throws IOException {
//		return findFreePort(DEFAULT_PORT);
//	}

//	/**
//	 * 求得一个没有使用的端口
//	 * 
//	 * @param initPort
//	 *            The first port to try, before resolving to brute force
//	 *            searching
//	 * @throws IOException
//	 * 
//	 * @throws IOException
//	 */
//	public static int findFreePort(int initPort) throws IOException {
//		int port = -1;
//		ServerSocket tmpSocket = null;
//		// first try the default port
//		try {
//			tmpSocket = new ServerSocket(initPort);
//
//			port = initPort;
//
//			log.debug("Using default port: " + port);
//		} catch (IOException e) {
//			log.debug("Failed to use specified port");
//			// didn't work, try to find one dynamically
//			try {
//				int attempts = 0;
//
//				while (port < 1024 && attempts < 2000) {
//					attempts++;
//
//					tmpSocket = new ServerSocket(0);
//
//					port = tmpSocket.getLocalPort();
//				}
//
//			} catch (IOException e1) {
//				throw new IOException(
//						"Failed to find a port to use for testing: "
//								+ e1.getMessage());
//			}
//		} finally {
//			if (tmpSocket != null) {
//				try {
//					tmpSocket.close();
//				} catch (IOException e) {
//					// ignore
//				}
//				tmpSocket = null;
//			}
//		}
//
//		return port;
//	}

	public static String[] getHostAddresses() throws Exception {
		Enumeration<NetworkInterface> nifs = NetworkInterface
				.getNetworkInterfaces();

		List<String> hostIps = new ArrayList<String>();
		while (nifs.hasMoreElements()) {
			NetworkInterface nif = (NetworkInterface) nifs.nextElement();
			Enumeration<InetAddress> ips = nif.getInetAddresses();

			while (ips.hasMoreElements()) {
				InetAddress ip = (InetAddress) ips.nextElement();
				if (ip instanceof java.net.Inet4Address) {
					hostIps.add(ip.getHostAddress());
				} // IPv6 not tested
			}
		}

		return hostIps.toArray(new String[0]);
	}

	/**
	 * 求非本地地址的ip
	 * */
	public static InetAddress findNonLocalhostIp() throws Exception {
		Enumeration<NetworkInterface> nifs = NetworkInterface
				.getNetworkInterfaces();
		InetAddress localhost=null;
		while (nifs.hasMoreElements()) {
			NetworkInterface nif = (NetworkInterface) nifs.nextElement();
			Enumeration<InetAddress> ips = nif.getInetAddresses();

			while (ips.hasMoreElements()) {
				InetAddress ip = (InetAddress) ips.nextElement();
				if(ip.toString().contains("127.")){
				    localhost=ip;
				}
			
				
				if (ip instanceof java.net.Inet4Address
						&& !ip.isLoopbackAddress()) {
					return ip;
				} // IPv6 not tested
			}
		}

		return localhost;
	}
}
