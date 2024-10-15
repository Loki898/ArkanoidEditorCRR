<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <!-- Definir la salida como HTML -->
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    
    <!-- Template principal que coincide con la raíz del documento -->
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
                <!-- Recorrer cada nivel dentro de "levels" -->
                <xsl:for-each select="levels/levels/entry">
                    <h1>Name: <xsl:value-of select="value/name"/></h1>
                <h1>Background Position: <xsl:value-of select="value/backgroundPosition"/></h1>
                <h1>Height: <xsl:value-of select="value/size/height"/></h1>
                <h1>Width: <xsl:value-of select="value/size/width"/></h1>
                <h1>Sound: <xsl:value-of select="value/sound"/></h1>
                <h1>Time: <xsl:value-of select="value/time"/></h1>

                <!-- Crear la tabla para mostrar los blocks -->
                <table>
                    
                    <!-- Fila con los valores de los items dentro de cada blocks -->
                    
                    <xsl:for-each select="value/blocks">
                        <tr>        
                                <!-- Para cada item dentro de un bloque -->
                            <xsl:for-each select="item">
                                <td>
                                    <xsl:choose>
                                        <!-- Si el item tiene un valor, mostrarlo -->
                                        <xsl:when test="value">
                                            <xsl:value-of select="value"/><br/>
                                        </xsl:when>
                                        <!-- Si no tiene valor, mostrar un espacio vacío usando la entidad declarada -->
                                        <xsl:otherwise>
                                            <br/>
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