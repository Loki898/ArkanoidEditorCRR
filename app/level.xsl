<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!-- Definir la salida como HTML -->
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    
    <!-- Template principal que coincide con la raíz del documento -->
    <xsl:template match="/">
        <html>
            <head>
                <title><xsl:value-of select="level/name"/></title>
                <style>
                    table, th, td {border: 1px solid black; padding: 8px;}
                    th {background-color: lightgray;}
                </style>
            </head>
            <body>
                <!-- Mostrar los elementos fuera de blocks en h1 -->
                <h1>Name: <xsl:value-of select="level/name"/></h1>
                <h1>Background Position: <xsl:value-of select="level/backgroundPosition"/></h1>
                <h1>Height: <xsl:value-of select="level/size/height"/></h1>
                <h1>Width: <xsl:value-of select="level/size/width"/></h1>
                <h1>Sound: <xsl:value-of select="level/sound"/></h1>
                <h1>Time: <xsl:value-of select="level/time"/></h1>

                <!-- Crear la tabla para mostrar los blocks -->
                <table>
                    
                    <!-- Fila con los valores de los items dentro de cada blocks -->
                    
                    <xsl:for-each select="level/blocks">
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
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>