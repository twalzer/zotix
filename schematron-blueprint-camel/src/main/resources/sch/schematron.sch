<?xml version="1.0" encoding="utf-8"?>
<schema xmlns="http://purl.oclc.org/dsdl/schematron"
	queryBinding="xslt2" schemaVersion="ISO19757-3">
    <title>Test ISO schematron file. Introduction mode</title>
    <ns prefix="dp" uri="http://www.dpawson.co.uk/ns#"/>
    
    <!-- Your constraints go here -->
    <pattern>
        <rule context="chapter">
            <assert test="title">A chapter should have a title</assert>
        </rule>
    </pattern>
</schema>