<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Levels Information</title>
                <style>
                    table, th, td {border: 1px solid black; border-collapse: collapse; padding: 8px;}
                    th {background-color: lightgray;}
                    td {text-align: center;}
                </style>
            </head>
            <body>
                <h1>Levels</h1>
                <xsl:for-each select="levels/levels/entry">
                    <h1>Name: <xsl:value-of select="value/name"/>
                    </h1>
                    <h1>Background Position: <xsl:value-of select="value/backgroundPosition"/>
                    </h1>
                    <h1>Height: <xsl:value-of select="value/size/height"/>
                    </h1>
                    <h1>Width: <xsl:value-of select="value/size/width"/>
                    </h1>
                    <h1>Sound: <xsl:value-of select="value/sound"/>
                    </h1>
                    <h1>Time: <xsl:value-of select="value/time"/>
                    </h1>

                    <table>


                        <xsl:for-each select="value/blocks">
                            <tr>
                                <xsl:for-each select="item">
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="value = 'Red'">
                                                <xsl:attribute name="style">background-color: red;</xsl:attribute>
                                            </xsl:when>
                                            <xsl:when test="value = 'White'">
                                                <xsl:attribute name="style">background-color: white;</xsl:attribute>
                                            </xsl:when>
                                            <xsl:when test="value = 'Cyan'">
                                                <xsl:attribute name="style">background-color: cyan;</xsl:attribute>
                                            </xsl:when>
                                            <xsl:when test="value = 'Blue'">
                                                <xsl:attribute name="style">background-color: blue; color: white;</xsl:attribute>
                                            </xsl:when>
                                            <xsl:when test="value = 'Yellow'">
                                                <xsl:attribute name="style">background-color: yellow;</xsl:attribute>
                                            </xsl:when>
                                            <xsl:when test="value = 'Magenta'">
                                                <xsl:attribute name="style">background-color: magenta;</xsl:attribute>
                                            </xsl:when>
                                            <xsl:when test="value = 'Orange'">
                                                <xsl:attribute name="style">background-color: orange;</xsl:attribute>
                                            </xsl:when>
                                            <xsl:when test="value = 'Green'">
                                                <xsl:attribute name="style">background-color: green;</xsl:attribute>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:attribute name="style">background-color: transparent;</xsl:attribute>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </xsl:for-each>

                            </tr>
                        </xsl:for-each>
                    </table>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
