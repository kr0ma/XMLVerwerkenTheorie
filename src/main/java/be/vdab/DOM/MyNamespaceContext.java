package be.vdab.DOM;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

class MyNamespaceContext implements NamespaceContext {
	private final Map<String, String> prefixesNamespaces = new HashMap<>();

	MyNamespaceContext() {
		prefixesNamespaces.put("vliegtuig", "www.aircraftcarriers.com/travels");
		prefixesNamespaces.put("hotel", "www.hotels.com/bookings");
	}

	@Override
	public String getNamespaceURI(String prefix) {
		String namespace = prefixesNamespaces.get(prefix);
		if (namespace == null) {
			throw new IllegalArgumentException("unknown prefix");
		}
		return namespace;
	}

	@Override
	public String getPrefix(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Iterator getPrefixes(String namespaceURI) {
		throw new UnsupportedOperationException();
	}
}
